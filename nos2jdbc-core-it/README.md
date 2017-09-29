# nos2jdbc-it-core
## nos2jdbc-core's Integrated Test
Maven Projectです。 MavenのProfileでDB Engineを指定してtestを実行します。   
pom.xmlには、hsqldb, h2, postgre, mysql, oracle, db2, mssql2005, standaredのProfileが定義されています。   
### 事前準備
* [nos2jdbc(No Seasar2 Container's S2JDBC)](https://github.com/ns2j/nos2jdbc)をMavenのローカルリポジトリへインストール。   
* 使うDB Engineにしたがって, sql/create_\*.sqlを使いTest DBを作り,
src/test/resources/nos2jdbc-datasource-\*.prorertiesを編集して作ったDBと合わせる。   
### Test実行
`mvn test -Pプロファイル`   
