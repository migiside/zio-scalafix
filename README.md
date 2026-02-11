# zio-scalafix

A collection of [Scalafix](https://scalacenter.github.io/scalafix/) rules for [ZIO](https://zio.dev/).

While [zio-intellij](https://github.com/zio/zio-intellij) provides similar inspections and refactorings within the IDE, they cannot be used in CI environments. This project provides Scalafix rules to automate these improvements in any environment, including CI pipelines.

These rules help you write more idiomatic and concise ZIO code by replacing common patterns with their more specialized equivalents.

## Rules

### PreferZIOAs

Replaces `map(_ => value)` with `as(value)`.

```scala
// Before
ZIO.succeed(1).map(_ => "a")

// After
ZIO.succeed(1).as("a")
```

### PreferZIOSome

Replaces `ZIO.succeed(Some(value))` with `ZIO.some(value)`.

```scala
// Before
ZIO.succeed(Some(1))

// After
ZIO.some(1)
```

### PreferZIONone

Replaces `ZIO.succeed(None)` with `ZIO.none`.

```scala
// Before
ZIO.succeed(None)

// After
ZIO.none
```

### PreferZIOZipRight

Replaces `flatMap(_ => zio)` with `*> zio`.

```scala
// Before
zio1.flatMap(_ => zio2)

// After
zio1 *> zio2
```

### PreferZIOOrElseFail

Replaces `orElse(ZIO.fail(e))` or `mapError(_ => e)` with `orElseFail(e)`.

```scala
// Before
zio.orElse(ZIO.fail("error"))
zio.mapError(_ => "error")

// After
zio.orElseFail("error")
```

### PreferZIOUnit

Replaces `ZIO.succeed(())` with `ZIO.unit`.

```scala
// Before
ZIO.succeed(())

// After
ZIO.unit
```

### PreferZIOIgnore

Replaces `catchAll(_ => ZIO.unit)` with `ignore`.

```scala
// Before
zio.catchAll(_ => ZIO.unit)

// After
zio.ignore
```
