# cc-web-runner

[Closure Compiler](https://developers.google.com/closure/compiler/) web runner.

Download [latest](https://github.com/monai/cc-web-runner/releases) release.

## Build

```bash
mvn clean compile package
```

Produces standalone JAR.

## Run

```bash
java -jar target/cc-web-runner-1.0.4-SNAPSHOT.jar
```

## Use

Request:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "optimizations": {
    "level": "SIMPLE_OPTIMIZATIONS"
  },
  "sources": [{
    "fileName": "bar.js",
    "code": "(console.log(function(){return 42-9;}));"
  }]
}
' "http://localhost:8080/compile"
```

Response:

```bash
{
  "result":{
    "debugLog":"",
    "errors":[
    ],
    "success":true,
    "variableMap":{
      "newNameToOriginalNameMap":{
      },
      "originalNameToNewNameMap":{
      }
    },
    "warnings":[
    ]
  },
  "source":"console.log(function(){return 33});",
  "status":"SUCCESS"
}
```

## Request

- `externs` - array of file objects
- `sources` - array of file objects
- `optimizations`
  - `level` - is of [CompilationLevel](https://github.com/google/closure-compiler/blob/29bbd198f0bf4967e4f406674b3eaf302a1f16a4/src/com/google/javascript/jscomp/CompilationLevel.java) type
  - `debug` - true|false, whether to call `setDebugOptionsForCompilationLevel`
  - `typeBased` - true|false, whether to call `setTypeBasedOptimizationOptions`
  - `wrappedOutput` - true|false, whether to call `setWrappedOutputOptimizations`
- `options` - directly deserialized to [CompilerOptions](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/CompilerOptions.java) class

File object

- `fileName` - file name
- `code` - file content

Add `?debug` query parameter to get exception object with error response.

## Response

- `result` - directly serialized from [Result](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/Result.java) class
- `source` - compiled source
- `status` - SUCCESS|ERROR
- `message` - error message if status is 'ERROR'

## License

ISC
