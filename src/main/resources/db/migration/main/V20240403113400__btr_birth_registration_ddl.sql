CREATE TABLE eg_bt_registration(
  id character varying(64) PRIMARY KEY,
  tenantId character varying(64),
  applicationNumber character varying(64) UNIQUE,
  babyFirstName character varying(64),
  babyLastName character varying(64),
  fatherId character varying(64),
  motherId character varying(64),
  doctorName character varying(64),
  hospitalName character varying(64),
  placeOfBirth character varying(64),
  timeOfBirth bigint,
  createdBy character varying(64),
  lastModifiedBy character varying(64),
  createdTime bigint,
  lastModifiedTime bigint
);


CREATE TABLE eg_btr_address_map(
   id character varying(64) PRIMARY KEY,
   tenantId character varying(64),
   applicationNumber character varying(64),
   CONSTRAINT fk_eg_btr_address_map FOREIGN KEY (applicationNumber) REFERENCES eg_bt_registration(applicationNumber)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);


CREATE TABLE eg_bt_address(
   id character varying(64) PRIMARY KEY,
   tenantId character varying(64),
   doorNo character varying(64),
   latitude FLOAT,
   longitude FLOAT,
   addressNumber character varying(64),
   addressLine1 character varying(256),
   addressLine2 character varying(256),
   landmark character varying(64),
   street character varying(64),
   city character varying(64),
   pincode character varying(64),
   detail character varying(64),
   registrationId character varying(64),
   createdBy character varying(64),
   lastModifiedBy character varying(64),
   createdTime bigint,
   lastModifiedTime bigint,
   CONSTRAINT fk_eg_bt_address FOREIGN KEY (registrationId) REFERENCES eg_btr_address_map(id)
     ON UPDATE CASCADE
     ON DELETE CASCADE
);
