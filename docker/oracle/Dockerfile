FROM container-registry.oracle.com/database/enterprise:19.3.0.0

RUN echo "DISABLE_OOB=ON" >> /opt/oracle/product/19c/dbhome_1/network/admin/sqlnet.ora

COPY --chown=oracle:oracle ./scripts /docker-entrypoint-initdb.d/setup

COPY --chown=oracle:oracle ./dump /dump/