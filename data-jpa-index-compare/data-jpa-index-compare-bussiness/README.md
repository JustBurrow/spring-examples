# data-jpa-mysql-index-compare

인덱스 종류에 따른 성능 비교.

부모 - 자식 관계의 엔티티가 있을 때, 참조 키 데이터 타입에 따른 성능 비교.

1. `BIGINT AUTO_INCREMENT` PK - Composite PK `BIGINT`, `INT`
2. `BIGINT AUTO_INCREMENT` PK - Alternative PK `BIGINT AUTO_INCREMENT`, Index `BIGINT`, `INT`
3. `CHAR` PK - Composite PK `CHAR`, `INT`
4. `CHAR` PK - Alternative PK `BIGINT AUTO_INCREMENT`, Index `CHAR`, `INT`
5. `VARCHAR` PK - Composite PK `VARCHAR`, `INT`
6. `VARCHAR` PK - Alternative PK `BIGINT AUTO_INCREMENT`, Index `VARCHAR`, `INT`
7. Alternative PK `BIGINT AUTO_INCREMENT`, not null unique(`VARCHAR`, `VARCHAR`) - Composite PK `VARCHAR`, `VARCHAR`, `INT`
8. Alternative PK `BIGINT AUTO_INCREMENT`, not null unique(`VARCHAR`, `VARCHAR`) - Alternative PK `BIGINT AUTO_INCREMENT`, Index `VARCHAR`, `VARCHAR`, `INT`
