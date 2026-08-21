import * as p from "@clack/prompts";
import logo from "./components/logo.js";
import absoluteCinema from "./components/absoluteCinema.js"
import playersMenu from "./menus/players.js";

async function main() {
  while (true) {
    console.clear();
    logo();
    const option = await p.select({
      message: "What do you want to do?",
      options: [
        { value: "ranking", label: "Ranking" },
        { value: "exit", label: "Exit" },
      ],
    });

    if (option === "exit") {
      console.clear();
      absoluteCinema();
      p.outro("Absolute Cinema! Yeah!");
      break;
    }

    if (option === "ranking") {
      await playersMenu();
    }
  }
}

main();
