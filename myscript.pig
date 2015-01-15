-- myscript.pig
REGISTER myudfs.jar;
A = LOAD '$input' AS (name: chararray);
B = FOREACH A GENERATE flatten(myudfs.UPPER(name)) AS word;
C = group B by word;
D = foreach C generate COUNT(B),group;
E = ORDER D BY $0 DESC;
F = LIMIT E 100;
store F into '$output';
