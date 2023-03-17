import { readFileSync } from "fs";
let file = readFileSync('./input.txt', 'utf-8');
//let file = readFileSync("./test.txt", "utf-8");
let lines = file.split("\n");
const locs = new Set<string>();
const paths: string[][] = [];
for (let i = 0; i < lines.length; i++) {
  paths.push(lines[i].trim().split(" -> "));
}

paths.forEach((path) => {
  addPathToLocs(path);
});

const lowestY = calcLowestRockYval(locs);
let sandLoc = [500, 0];
let ans = 0;
while (true) {

 if (sandLoc[1] > lowestY) {
    ans++;
    locs.add("" + sandLoc[0] + "," + sandLoc[1]);
    sandLoc[0] = 500;
    sandLoc[1] = 0;
  } else if (!locs.has("" + sandLoc[0] + "," + (sandLoc[1] + 1))) {
    sandLoc[1]++;
  } else if (!locs.has("" + (sandLoc[0] - 1) + "," + (sandLoc[1] + 1))) {
    sandLoc[0]--;
    sandLoc[1]++;
  } else if (!locs.has("" + (sandLoc[0] + 1) + "," + (sandLoc[1] + 1))) {
    sandLoc[0]++;
    sandLoc[1]++;
  } else {
    if (sandLoc[1] == 0) {
      ans++;
      break;
    }
    ans++;
    locs.add("" + sandLoc[0] + "," + sandLoc[1]);
    sandLoc[0] = 500;
    sandLoc[1] = 0;
  }
}
console.log(ans);


//helpers
function calcLowestRockYval(locs: Set<string>): number {
  let yVals: number[] = [];
  locs.forEach((loc) => {
    yVals.push(Number(loc.split(",")[1]));
  });
  return Math.max(...yVals); //greater val means lower in space
}

function addPathToLocs(path: string[]) {
  for (let i = 0; i < path.length - 1; i++) {
    const coords1 = path[i].split(",");
    const coords2 = path[i + 1].split(",");
    const x1 = Number(coords1[0]);
    const y1 = Number(coords1[1]);
    const x2 = Number(coords2[0]);
    const y2 = Number(coords2[1]);
    if (x1 !== x2) {
      for (let i = Math.min(x1, x2); i < Math.max(x1, x2) + 1; i++) {
        locs.add(`${i},${y1}`);
      }
    } else {
      for (let i = Math.min(y1, y2); i < Math.max(y1, y2) + 1; i++) {
        locs.add(`${x1},${i}`);
      }
    }
  }
}
