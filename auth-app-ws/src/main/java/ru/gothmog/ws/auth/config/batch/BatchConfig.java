package ru.gothmog.ws.auth.config.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import ru.gothmog.ws.auth.core.batch.UserListener;
import ru.gothmog.ws.auth.core.batch.separator.BlankLineRecordSeparatorPolicy;
import ru.gothmog.ws.auth.core.model.auth.User;
import ru.gothmog.ws.auth.core.model.files.ImportData;
import ru.gothmog.ws.auth.core.repository.UserRepository;
import ru.gothmog.ws.auth.rest.facade.files.FileStorageFacade;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageFacade fileStorageFacade;
    @Autowired
    private StepBuilderFactory sbf;
    @Autowired
    private JobBuilderFactory jbf;

    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/users.csv"));

        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(DELIMITER_COMMA);
                setNames("name", "number", "amount", "discount", "location");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(User.class);
            }});
        }});

        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());

        return reader;
    }

    @Bean
    @Scope(value = "step", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public FlatFileItemReader<ImportData> importDataReader(@Value("#{jobParameters[fullPathFileName]}") String pathToFile){
        FlatFileItemReader<ImportData> reader = new FlatFileItemReader<>();


        reader.setResource(new FileSystemResource(pathToFile));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<ImportData>() {{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"idLocality","idKeyInformName","valueAll","reportingPeriod","createDate"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<ImportData>(){{
                setTargetType(ImportData.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemWriter<User> writer() {
        // return new InvoiceItemWriter(); // Using lambda expression code instead of a separate implementation
        return users -> {
            log.info("Saving users Records: {}", users);
            userRepository.saveAll(users);
        };
    }

    //Processor class Object
    @Bean
    public ItemProcessor<User, User> processor() {
        // return new InvoiceProcessor(); // Using lambda expression code instead of a separate implementation
        return user -> {

            return user;
        };
    }

    //Listener class Object
    @Bean
    public JobExecutionListener listener() {
        return new UserListener();
    }

    //Step Object
    @Bean
    public Step stepA() {
        return sbf.get("stepA")
                .<User, User>chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    //Job Object
    @Bean
    public Job jobA() {
        return jbf.get("jobA")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(stepA())
                // .next(stepB())
                // .next(stepC())
                .build();
    }

//    @Bean
//    public JdbcBatchItemWriter<ImportData> writer(){
//        String sql = "insert into import_data(id_locality, id_key_inform_name, value_all, reporting_period, create_date) VALUES (?,?,?,?,?)";
//        JdbcBatchItemWriter<ImportData> writerImportData = new JdbcBatchItemWriter<>();
//        writerImportData.setDataSource(dataSource);
//        writerImportData.setSql(sql);
//        ItemPreparedStatementSetter<ImportData> statementSetter = new ImportDataPreparedStatementSetter();
//        writerImportData.setItemPreparedStatementSetter(statementSetter);
//        return writerImportData;
//    }
//    @Bean(name = "importDataJob")
//    @Primary
//    public Job importDataJob(ItemReader<ImportData> importDataReader){
//        return jobBuilderFactory.get("importDataJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(stepImportData(importDataReader))
//                .end()
//                .build();
//    }
//    @Bean
//    public Step stepImportData(ItemReader<ImportData> importDataReader){
//        return stepBuilderFactory.get("stepImportData").<ImportData,ImportData>chunk(100)
//                .reader(importDataReader)
//                .writer(writer())
//                .build();
//    }
}
