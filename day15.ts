import { readFileSync } from "fs";
//let file = readFileSync('./input.txt', 'utf-8');
let file = readFileSync("./test.txt", "utf-8");
let lines = file.split("\n");
let nums: number[][] = []; //sensor x, y, beacon x, y
let regex = /(-?\d+)/g;
for (let i = 0; i < lines.length; i++) {
  let match = lines[i].match(regex);
  nums.push(match!.map((n) => Number(n)));
}
console.log(nums);



function rectDist(x1: number, y1: number, x2: number, y2: number): number{
    return Math.abs(x1-x2) + Math.abs(y1-y2);
}