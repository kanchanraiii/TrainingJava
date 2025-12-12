// splicing an array
var myArray=[2,3,0,1,7,4];
var mySplice=myArray.splice(3,5);
console.log(mySplice);

// using an array as a stack 
var myStack=[];
myStack.push(4);
myStack.push(6);
myStack.push(0);
myStack.push(2);
myStack.pop();
console.log(myStack);

// using arrays as queues
var myQueue=[];
myQueue.push(1);
myQueue.push(2);
myQueue.push(3);
console.log(myQueue.shift());
console.log(myQueue);
