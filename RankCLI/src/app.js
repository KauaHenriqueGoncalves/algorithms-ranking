import * as p from "@clack/prompts";
import logo from "./components/logo.js";
import absoluteCinema from "./components/absoluteCinema.js";
import playersMenu from "./menus/players.js";

async function main() {
  while (true) {
    console.clear();
    logo();
    const option = await p.select({
      message: "What gonna do, Mr Dear?",
      options: [
        { value: "rank-api", label: "RankApi" },
        { value: "exit", label: "Exit" },
      ],
    });

    if (option === "exit") {
      console.clear();
      absoluteCinema();
      p.outro("Absolute Cinema! Yeah!");
      break;
    }

    if (option === "rank-api") {
      await playersMenu();
    }
  }
}

main();
