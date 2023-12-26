create table FOO_APIDetails (
	uuid_ VARCHAR(75) null,
	ID LONG not null primary key,
	CreateDate DATE null,
	UserID LONG,
	Path VARCHAR(75) null,
	Method VARCHAR(75) null,
	RequestBody VARCHAR(75) null,
	ResponseCode INTEGER,
	ResponseMessage VARCHAR(75) null
);