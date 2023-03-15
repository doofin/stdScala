package com.doofin.stdScala.dataTypes

import scala.collection.mutable.ArrayBuffer

/** tree and binary tree */
object Tree {

  /** tree */
  case class TreeNode[T](
      val value: T,
      val children: ArrayBuffer[TreeNode[T]] = ArrayBuffer[TreeNode[T]]()
  )

  /** binary tree */
  case class BinTreeNode[T](
      val value: T,
      val left: BinTreeNode[T],
      val right: BinTreeNode[T]
  )
}
