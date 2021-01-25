package com.doofin.stdScala

import boopickle.UnpickleImpl

import scala.util.Try

import java.io._
import java.nio.ByteBuffer
import java.util.Base64._

import boopickle.DefaultBasic._

trait stdSerializers {
  object objSerl {

    def write[t](obj: t, path: String) = {
      val out = new ObjectOutputStream(new FileOutputStream(path))
      out.writeObject(obj)
      out.close()
    }

    def read[t](path: String) = {
      val in = new ObjectInputStream(new FileInputStream(path))
      val fooToRead = in.readObject()
      in.close()
      fooToRead.asInstanceOf[t]
    }

    //    assert(fooToWrite == fooToRead)
  }
  object boopickT {

    def t2str[T](pck: Pickler[T])(t: T): String = {
      implicit val p: Pickler[T] = pck
      val r: ByteBuffer = Pickle.intoBytes[T](t)
      val arr = new Array[Byte](r.remaining()) //bug in js
      r.get(arr) //copy r to arr
      getEncoder.encodeToString(arr)
    }

    def str2t[T](pck: Pickler[T])(r: String): Try[T] = {
      implicit val p: Pickler[T] = pck
      UnpickleImpl[T].tryFromBytes(ByteBuffer.wrap(getDecoder.decode(r)))
    }

    def bb2t[T](pck: Pickler[T])(r: ByteBuffer): T =
      UnpickleImpl[T](pck).tryFromBytes(r).get

    def t2bb[T](pck: Pickler[T])(t: T): ByteBuffer = {
      implicit val p: Pickler[T] = pck
      Pickle.intoBytes[T](t)
    }
  }
}
