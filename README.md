# dropwizard-logging-extensions

More logging appenders for dropwizard.

# console-json

Log as json on console.

How to use? Just add the ```console-json``` logging appender in your config like this:
```
logging:
  appenders:
    - type: console-json
```

Example output:
```
{timestamp=1446821655256, level=INFO, thread=main, logger=org.eclipse.jetty.util.log, message=Logging initialized @1026ms, context=default}
```