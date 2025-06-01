for /f "delims=" %%x in (properties-local.env) do (set "%%x")
.\mvnw package

