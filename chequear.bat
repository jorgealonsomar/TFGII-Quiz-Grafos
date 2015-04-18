if not exist doccheck mkdir doccheck

javadoc -d doccheck ^
-doclet com.sun.tools.doclets.doccheck.DocCheck ^
-classpath .\lib\junit-4.8.2.jar ^
-docletpath .\lib\doccheck.jar ^
-private ^
-sourcepath .\src ^
-subpackages preguntas ^
test ^
utilidades ^
interfaz

