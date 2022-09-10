CREATE SCHEMA IF NOT EXISTS auth AUTHORIZATION root;
CREATE SCHEMA IF NOT EXISTS batch_schema AUTHORIZATION root;


CREATE TABLE IF NOT EXISTS auth.users
(
    id         bigserial                           NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status     boolean   DEFAULT false             NOT NULL,
    username   varchar(150)                        NOT NULL,
    email      varchar(150)                        NOT NULL,
    pswd       varchar(255)                        NOT NULL,
    CONSTRAINT pk_users_id PRIMARY KEY (id),
    CONSTRAINT unq_users_username_key UNIQUE (username),
    CONSTRAINT unq_users_email_key UNIQUE (email),
    CONSTRAINT unq_users_email_username_key UNIQUE (email, username)
)
    WITH (
        OIDS= FALSE
    );
ALTER TABLE auth.users
    OWNER TO root;

CREATE TABLE IF NOT EXISTS auth.roles
(
    id         bigserial                           NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    status     boolean   DEFAULT false             NOT NULL,
    role_name  varchar(100)                        NOT NULL,
    CONSTRAINT pk_roles_id PRIMARY KEY (id),
    CONSTRAINT unq_roles_role_name_key UNIQUE (role_name)
)
    WITH (
        OIDS= FALSE
    );
ALTER TABLE auth.roles
    OWNER TO root;

CREATE TABLE IF NOT EXISTS auth.users_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
)
    WITH (
        OIDS= FALSE
    );
ALTER TABLE auth.users_roles
    OWNER TO root;

ALTER TABLE auth.users_roles
    ADD CONSTRAINT fk_user_roles_users FOREIGN KEY (user_id) REFERENCES auth.users (id) ON DELETE CASCADE ON UPDATE RESTRICT;

ALTER TABLE auth.users_roles
    ADD CONSTRAINT fk_user_roles_roles FOREIGN KEY (role_id) REFERENCES auth.roles (id) ON DELETE CASCADE ON UPDATE RESTRICT;


INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_ADMIN');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_AGRONOMIST');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_ANONYMOUS');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_INVESTOR');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_SPK_REPRESENTATIVE');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_TK_REPRESENTATIVE');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_USER');
INSERT INTO auth.roles (status, role_name)
values (true, 'ROLE_FARMER');
