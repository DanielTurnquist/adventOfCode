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
let result = 0; //-1: wrong order 0: same 1: right
let total = 0;
for (let i = 0; i < structures.length; i++) {
    result = 0;
    compare(structures[i][0], structures[i][1]);
    if (result === 1) {
        console.log(i+1);
        total += i + 1;
    }
}
console.log(total);

            //utilities//

function compare(left: any[], right: any[]){
    if (result !== 0) {
        return;
    }
    for (let i = 0; i < Math.min(left.length, right.length); i++) {
        let leftEle = left[i];
        let rightEle = right[i];
        if (Number.isInteger(leftEle) && Number.isInteger(rightEle)) {//both are nums
            if (leftEle < rightEle) {
                result = 1;
                return;
            }
            else if (leftEle > rightEle){
                result = -1;
                return;
            }
        }
        else if (!Number.isInteger(leftEle) && !Number.isInteger(rightEle)){//both are lists
            compare(leftEle, rightEle);
        }
        else if (Number.isInteger(leftEle) && !Number.isInteger(rightEle)){//left is a num, right is a list
            compare([leftEle], rightEle);
        }
        else if (!Number.isInteger(leftEle) && Number.isInteger(rightEle)){//left is a list, right is a num
            compare(leftEle, [rightEle]);
        }
    }
    if (left.length > right.length){
        result = -1;
    }
    if (left.length < right.length){
        result = 1;
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