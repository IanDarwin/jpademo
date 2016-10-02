all: MySQL.html	PostgreSQL.html README.html
MySQL.html: MySQL.adoc
	asciidoctor $?
PostgreSQL.html: PostgreSQL.adoc
	asciidoctor $?
README.html: README.adoc
	asciidoctor $?
