# stdScala

a concise library with debug, math and others utils for scala 3

published on jitpack

# usage : 
find latest commit at [![](https://jitpack.io/v/doofin/stdScala.svg)](https://jitpack.io/#doofin/stdScala)

add following in sbt :

`resolvers += "jitpack" at "https://jitpack.io"`

`"com.github.doofin.stdScala" %% "stdscala"  % "2515133003"` or replace last string with latest commit

## in JVM

`import com.doofin.stdScalaCross.*`

## in JS
`import com.doofin.stdScalaJvm.*`

# content

## dbg print similar to it in rust : 

![image](https://user-images.githubusercontent.com/6041353/211036426-735c9124-41df-41ff-a738-9c85b1acff4d.png)

will give 

![image](https://user-images.githubusercontent.com/6041353/211036338-e0527e35-53b2-4372-8416-1dcc4a6add5e.png)


## math ops

logical connectives like `def or(bs: Boolean*) ` and 2D vectors

## jvm utils

read,write text file

# contributing

since it's a cross project, the main shared codebase reside in https://github.com/doofin/stdScala/tree/master/shared/src/main/scala/com/doofin/stdScala


the jvm specific code is at https://github.com/doofin/stdScala/tree/master/jvm/src/main/scala/com/doofin/stdScala


start by following the entry at https://github.com/doofin/stdScala/blob/master/shared/src/main/scala/com/doofin/stdScala/stdScalaCross.scala  with metals IDE 