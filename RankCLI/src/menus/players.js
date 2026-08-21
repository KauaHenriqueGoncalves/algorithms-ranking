import * as p from "@clack/prompts";
import * as s from "../services/api.js";

function _showTable(players) {
  if (!players || players.length === 0) {
    p.log.warn("Nenhum jogador encontrado.");
    return;
  }
  players.forEach((j) => {
    p.log.info(`#${j.id} | ${j.name} | ${j.category} | ${j.score} pts`);
  });
}

export default async function playersMenu() {
  const action = await p.select({
    message: "Players",
    options: [
      { value: "getAllPlayers", label: "List Players" },
      { value: "getByName", label: "Search by Name" },
      { value: "create", label: "Create Player" },
      { value: "generate", label: "Generate Random Player" },
      { value: "generate_many", label: "Generate Multiple Players" },
      { value: "sort", label: "Sort Players" },
      { value: "back", label: "Back" },
    ],
  });

  switch (action) {
    case "getAllPlayers": {
      const players = await s.getAllPlayers();
      _showTable(players);
      break;
    }

    case "getByName": {
      const name = await p.text({ message: "Name of Player:" });
      const player = await s.getPlayerByName(name);
      if (!player) {
        p.log.error("Jogador não encontrado.");
      } else {
        _showTable([player]);
      }
      break;
    }

    case "create": {
      const name = await p.text({ message: "Name:" });
      const category = await p.select({
        message: "Category:",
        options: [
          { value: "Ouro", label: "Ouro" },
          { value: "Prata", label: "Prata" },
          { value: "Bronze", label: "Bronze" },
        ],
      });
      const score = await p.text({ message: "Score:" });
      const newPlayer = await s.createPlayer(name, category, Number(score));
      p.log.success(`Create player: #${newPlayer.id} ${newPlayer.name}`);
      break;
    }

    case "generate": {
      await s.generatePlayer();
      p.log.success("Random player generated.");
      break;
    }

    case "generate_many": {
      const quant = await p.text({ message: "Quant:" });
      const players = await s.generateManyPlayers(Number(quant));
      p.log.success(`${players.length} total players.`);
      break;
    }

    case "sort": {
      const type = await p.select({
        message: "Algorithm sorting:",
        options: [
          { value: "bubble", label: "Bubble Sort" },
          { value: "selection", label: "Selection Sort" },
          { value: "insertion", label: "Insertion Sort" },
        ],
      });
      const ranking = await s.sort(type);
      _showTable(ranking);
      break;
    }

    case "back":
    default:
      return;
  }

  await p.text({ message: "Press ENTER to continue..." });
}
