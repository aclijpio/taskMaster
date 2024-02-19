create table users_roles
(
    role_id integer not null,
    user_id bigint not null,
    foreign key (role_id) references roles(role_id),
    foreign key (user_id) references users(user_id)
);