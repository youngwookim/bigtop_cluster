import com.databricks.spark.sql.perf.tpcds.Tables

val bench_home = System.getenv("BENCH_HOME")
val hdfs_dest = "hdfs:///TPCDS-10TB"
val size = 10000

val tables = new Tables(sqlContext, bench_home + "/tpcds-kit/tools", size)
tables.genData(hdfs_dest, "parquet", true, true, true, true, true)
sqlContext.sql(s"DROP DATABASE IF EXISTS tpcds10tb CASCADE")
tables.createExternalTables(hdfs_dest, "parquet", "tpcds10tb", true)

exit