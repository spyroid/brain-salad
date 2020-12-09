import { readLines } from "https://deno.land/std@0.80.0/io/mod.ts";
import * as path from "https://deno.land/std@0.80.0/path/mod.ts";

// const filename = path.join(Deno.cwd(), "std/io/README.md");
let fileReader = await Deno.open('data.txt');

let s = new Set<string>();

for await (let line of readLines(fileReader)) {
//   console.log(line);
    s.add(line)
}

console.log(s)

