# Simplicité UI tests

## How to run 

Run the test like a maven suite with `mvn test` and the options of your choice. The URL of the target instance is required.

| Option                     | Default    | Possible values |
|----------------------------|------------|-----------------|
| -Dselenide.browser         | chrome     | chrome, firefox |
| -Dselenide.browserSize     | 2500x2000  |                 |
| -Dselenide.headless        | true       | true, false     |
| -Dselenide.savePageSource  | false      |                 |
| -Dselenide.pageLoadTimeout | 30000      |                 |
| -Dselenide.pollingInterval | 1000       |                 |
| -Dsimplicite.url           | `null`     | **Mandatory**   |
| -Dsimplicite.user          | designer   |                 |
| -Dsimplicite.password      | simplicite |                 |


Example:

```
mvn test -Dselenide.browser=firefox -Dselenide.headless=false -Dsimplicite.url=https://my.instance.com  -Dsimplicite.password=myownpassword
```

