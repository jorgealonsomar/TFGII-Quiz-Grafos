if not exist doccheck mkdir doccheck
javadoc -sourcepath src -d doc  -subpackages preguntas ^
test ^
utilidades ^
interfaz 
-encoding UTF-8 -version -author -private