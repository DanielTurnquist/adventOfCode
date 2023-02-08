import { readFileSync } from 'fs';
            //main//
let file = readFileSync('./input.txt', 'utf-8');
//let file = readFileSync('./test.txt', 'utf-8');
file = file.replace(/\r/g, "");
const stringPairs = file.split("\n\n");
//first, represent string data as actual structures
const structures: any = [];
let listStack: any[] = [];
listStack.push([]) //the outer list of every signal
let inNumber = false; //used to read numbers of more than 1 digit
let num = "";
stringPairs.forEach(pair => {
    structures.push(pairToStructure(pair));
});
let d1 = [[2]];
let d2 = [[6]]
structures.push(d1);
structures.push(d2);//divider packets
let structs: any[] = [];
let result = 0; //-1: wrong order 0: same 1: right
let key = 1;
for (let i = 0; i < structures.length; i++) {
    structs.push(structures[i][0]);
    structs.push(structures[i][1]);
}
// console.log(total);
let sorted:any[][] = structs.sort((l: any, r: any) => {
    if (compare(l, r)) {
        return -1;
    }
    return 1;
})
// for (let i = 0; i < sorted.length; i++) {
//     if (sorted[i] == d1 || sorted[i] == d2) {
//         key *= (i + 1);
//     };
// }
console.log(key);
            //utilities//
function compare(left: any[], right: any[]): boolean{
    result = 0;
    recCompare(left, right);
    if (result === 1) {
        return true;
    }
    return false;
}
function recCompare(left: any[], right: any[]){
    if (result !== 0) {
        return;
    }
    //console.log("Compare " + left + " vs " + right);
    for (let i = 0; i < Math.min(left.length, right.length); i++) {
        let leftEle = left[i];
        let rightEle = right[i];
        //console.log("Compare " + leftEle + " vs " + rightEle);
        if (Number.isInteger(leftEle) && Number.isInteger(rightEle)) {//both are nums
            if (leftEle < rightEle) {
                //console.log("Left side is smaller, so inputs are in the right order");
                result = 1;
                return;
            }
            else if (leftEle > rightEle){
                //console.log("Right side is smaller, so inputs are NOT in the right order");
                result = -1;
                return;
            }
        }
        else if (!Number.isInteger(leftEle) && !Number.isInteger(rightEle)){//both are lists
            recCompare(leftEle, rightEle);
        }
        else if (Number.isInteger(leftEle) && !Number.isInteger(rightEle)){//left is a num, right is a list
            recCompare([leftEle], rightEle);
        }
        else if (!Number.isInteger(leftEle) && Number.isInteger(rightEle)){//left is a list, right is a num
            recCompare(leftEle, [rightEle]);
        }
        if (result !== 0){
            break;
        }
    }
    if (result !== 0){
        return;
    }
    if (left.length > right.length){
        result = -1;
        //console.log("Right side ran out of items, so inputs are NOT in the right order");
        return;
    }
    if (left.length < right.length){
        //console.log("Left side ran out of items, so inputs are in the right order");
        result = 1;
        return;
    }
}

function toStructure(protoStructure: string): any{
    num = "";
    inNumber = false
    for (let i = 1; i < protoStructure.length - 1; i++){
        const character = protoStructure[i];
        let currentList = listStack[listStack.length-1];
        if(isNum(character) && isNum(protoStructure[i+1])){
            num += character;
            inNumber = true;
        }
        else if(isNum(character) && !isNum(protoStructure[i+1])){
            num += character;
            inNumber = false;
        } 
        else if (character === "["){
            const newList: any[] = [];
            currentList.push(newList);
            listStack.push(newList);
            inNumber = false;
            num = "";
        }
        else if (character === "]"){
            listStack.pop();
            inNumber = false;
            num = "";
        } else {
            inNumber = false;
            num = "";
        }
        if (num !== "" && !inNumber) {
            currentList.push(Number(num));
        }
    }
    const toReturn = listStack[0];
    listStack = [];
    listStack.push([]);
    return toReturn;
}

function pairToStructure(pair: string): any[]{
    const protoLists: string[] = pair.split("\n");
    const toReturn: any[] = [];
    toReturn.push(toStructure(protoLists[0]));
    toReturn.push(toStructure(protoLists[1]));
    return toReturn;
}

function isNum(numberToCheck: string){
    return !isNaN(Number(numberToCheck))
}
