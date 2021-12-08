# Help me

Fork the repo -> Fix/Add/Other -> Create PR into this repo -> Review -> Maybe accept -> Sweet!

## Thinks to fix

Right now you need to add the arguments in maven or manually add them to the run configuration in Intellij :/ 
If you know how to add those to maven builtin config that would be great!

# Install from source

``` shell
mvn clean install -Dconfig.dir=src/main/resources -Denv.dir=etc
```

# Compile from source

``` shell
mvn clean compile
```

# Run test

``` shell
mvn test -Dconfig.dir=src/main/resources -Denv.dir=etc
 ```