package com.doofin.stdScala.dataTypes

import scala.collection.mutable.ArrayBuffer

/* 4 ways to represent graph:
  set of node and edge
  adjacency matrix,
  adjacency list
  adjacency set
 */
object Graph {

  type NodeId = Long
  case class Edge(src: NodeId = 0L, tgt: NodeId = 0L)
  case class Node[T](id: NodeId, value: T)

  /** graph as set of node and edge */
  case class Graph[T](nodes: Seq[Node[T]], edges: Seq[Edge])

  def getNeibsUndirected[t](n: Node[t], g: Graph[t]) = {
    g.edges
      .filter(x => x.src == n.id || x.tgt == n.id)
      .flatMap(e => Seq(e.src, e.tgt))
      .toSet
  }

  def getNeibsDirected[t](n: Node[t], g: Graph[t]) = {
    g.edges
      .filter(x => x.src == n.id)
      .map(_.tgt)
      .toSet
  }
}
