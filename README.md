# cc-web-runner

[Closure Compiler](https://developers.google.com/closure/compiler/) web runner.

Download [latest](https://github.com/monai/cc-web-runner/releases) release.

## Build

```bash
mvn compile package
```

Produces 2 artifacts:

- WAR archive that can be deployed on servlet container
- Standalone executable JAR archive

## Run

```bash
java -jar target/cc-web-runner-standalone-1.0-SNAPSHOT.jar
```

## Use

Request:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "compilationLevelOptions": {
    "level": "SIMPLE_OPTIMIZATIONS"
  },
  "sources": [{
    "fileName": "bar.js",
    "code": "(console.log(function(){return 42-9;}));"
  }]
}
' "http://localhost:8080/compile"
```

## Endpoints

Request and response content types are `application/json`.

### GET /status

Returns Closure Compiler version.

Response:

- `compilerVersions` String - Closure Compiler version

### GET /options

Returns default options with given compilationLevelOptions.

Query parameters:

- `level` String - is of type [CompilationLevel](https://github.com/google/closure-compiler/blob/29bbd198f0bf4967e4f406674b3eaf302a1f16a4/src/com/google/javascript/jscomp/CompilationLevel.java), compilation level
- `debug` Boolean - whether to call `setDebugOptionsForCompilationLevel`
- `typeBased` Boolean - whether to call `setTypeBasedOptimizationOptions`
- `wrappedOutput` Boolean - whether to call `setWrappedOutputOptimizations`

Response:

- `options` Object - is of type [CompilerOptions](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/CompilerOptions.java), compiler options

### GET /externs

Returns default externs.

Response:

- `externs` Array - is of type List&lt;[SourceFile](https://github.com/google/closure-compiler/blob/master/src/com/google/javascript/jscomp/SourceFile.java)&gt;, an array of extern files

### POST /compile

Request:

- `externs` Array - `[{ fileName: String, code: String }]`, array of extern files
- `sources` Array - `[{ fileName: String, code: String }]`, array of source files to compile
- `compilationLevelOptions`
  - `level` String - is of type [CompilationLevel](https://github.com/google/closure-compiler/blob/29bbd198f0bf4967e4f406674b3eaf302a1f16a4/src/com/google/javascript/jscomp/CompilationLevel.java), compilation level
  - `debug` Boolean - whether to call `setDebugOptionsForCompilationLevel`
  - `typeBased` Boolean - whether to call `setTypeBasedOptimizationOptions`
  - `wrappedOutput` Boolean - whether to call `setWrappedOutputOptimizations`
- `options` Object - is of type [CompilerOptions](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/CompilerOptions.java), compiler options

Query parameters:

- `?debug` Boolean - whether to return error messages

Response:

- `result` Object - is of type [Result](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/Result.java), compilations results
- `source` String - compiled source
- `status` String - SUCCESS|ERROR
- `message` String - error message if the status is 'ERROR'
- `exception` Object - is of type Throwable, occurred exception

## License

ISC
