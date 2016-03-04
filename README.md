# cc-web-runner

[Closure Compiler](https://developers.google.com/closure/compiler/) web runner.

Download [latest](https://github.com/monai/cc-web-runner/releases) release.

## Build

```bash
mvn clean compile war:war
```

Produces Java Servlet 3.0 compatible *war* package that can be deployed on any compatible server, i.e. Tomcat.

## Run

```bash
mvn jetty:run
```

Embedded Jetty goal for development.

## Use

Request:

```bash
curl -X POST -H "Content-Type: application/json" -d '{
    "options": {
        "foldConstants": true
    },
    "externs": [{
        "fileName": "foo.js",
        "code": ""
    }],
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
    "idGeneratorMap":"",
    "success":true,
    "warnings":[
    ]
  },
  "source":"console.log(function(){return 33});",
  "status":"SUCCESS"
}
```

Options object is directly deserialized to [CompilerOptions](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/CompilerOptions.java) class.

Result object is directly serialized from [Result](https://github.com/google/closure-compiler/blob/v20160208/src/com/google/javascript/jscomp/Result.java) class.

Add `?debug` query parameter to get exception on error responses.

## License

ISC
