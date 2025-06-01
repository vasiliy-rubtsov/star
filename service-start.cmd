for /f "delims=" %%x in (properties-local.env) do (set "%%x")
java -jar .\target\star-%STAR_VERSION%.jar

