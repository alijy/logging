# logging

[![Build Status](https://travis-ci.org/hmrc/logging.svg?branch=master)](https://travis-ci.org/hmrc/logging) [ ![Download](https://api.bintray.com/packages/hmrc/releases/logging/images/download.svg) ](https://bintray.com/hmrc/releases/logging/_latestVersion)

This simple API provides a facade for SLF4J (yes I know that's also a facade). The value of this API is that the logging operations are easily TDD testable.

 1. Classes that need to use logging should receive a `SimpleLogger` via dependency injection.
 2. Wrap the underlying SLF4J logger in a `LoggerFacade` and inject this. 
 3. A test stub, `StubLogger`, is provided for you to use in your unit tests instead. You can inspect its content to verify that logging operations happened as expected.

There is (deliberately) no conditional logging provided here, although it still works at the underlying Logback output level. The cost of message formatting is normally relatively low. Pass expensive objects via the varargs parameter, in the manner normal for SLF4J, to avoid them being unnecessarily converted to strings when logging levels are low.

The `StubLogger` captures messages and arguments in `LogEntry`, which tests can inspect. Avoid problems with testing context-dependent values (e.g. time taken) by passing them in the argument list; the exact message contents can then be verified without recourse to fragile regular expressions etc. With this in mind, use the heuristic:

 * for values that are predictable, use Scala interpolation `log.info(s"Opened $file")`
 * for values that are not predictable, use varags `log.info("Parsing took {}.", timeTaken)`
 * blend these two as appropriate.

In each case, the `StubLogger` allows your tests to verify the message strings simply by testing equality, e.g.

```
    log.info(s"LOOKUP origin=$origin postcode=$postcode matches=$matches took {}", timeTaken)
```
then
```
    assert(log.size === 1)
    assert(log.infos.head.message === "LOOKUP origin=xyz postcode=BF1 0AX matches=1 took {}")
```

### Hint

As an alternative testable solution for TDD development, you might also consider [opslogger](https://github.com/EqualExperts/opslogger).

### License

Except for specified exceptions, this code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html"). `DiagnosticTimer` is licensed under the [MIT Licence](https://opensource.org/licenses/MIT).
