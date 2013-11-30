/*

Queue.js

A function to represent a queue

Created by Stephen Morley - http://code.stephenmorley.org/ - and released under
the terms of the CC0 1.0 Universal legal code:

http://creativecommons.org/publicdomain/zero/1.0/legalcode

*/

/* Creates a new queue. A queue is a first-in-first-out (FIFO) data structure -
* items are added to the end of the queue and removed from the front.
*/
define(function(require, exports) {
  function Queue() {
    // initialise the queue and offset
    this.queue = [];
    this.offset = 0;
  }

  /* Returns the length of the queue.
  */
  Queue.prototype.size = function() {
    // return the length of the queue
    return (this.queue.length - this.offset);

  }

  /* Returns true if the queue is empty, and false otherwise.
  */
  Queue.prototype.isEmpty = function() {
    // return whether the queue is empty
    return (this.queue.length == 0);

  }

  Queue.prototype.empty = Queue.prototype.isEmpty;

  /* Enqueues the specified item. The parameter is:
  *
  * item - the item to push
  */
  Queue.prototype.push = function(item) {
    // push the item
    this.queue.push(item);
  }

  /* Dequeues an item and returns it. If the queue is empty then undefined is
  * returned.
  */
  Queue.prototype.pop = function() {
    // if the queue is empty, return undefined
    if (this.queue.length == 0) return undefined;

    // store the item at the front of the queue
    var item = this.queue[this.offset];

    // increment the offset and remove the free space if necessary
    if (++this.offset * 2 >= this.queue.length) {
      this.queue = this.queue.slice(this.offset);
      this.offset = 0;
    }

    // return the dequeued item
    return item;
  }

  /* Returns the item at the front of the queue (without dequeuing it). If the
  * queue is empty then undefined is returned.
  */
  Queue.prototype.top = function() {
    // return the item at the front of the queue
    return (this.queue.length > 0 ? this.queue[this.offset] : undefined);
  }

  Queue.prototype.front = Queue.prototype.top;

  Queue.prototype.clear = function() {
    this.queue = [];
    this.offset = 0;
  }

  exports.Queue = Queue;
});