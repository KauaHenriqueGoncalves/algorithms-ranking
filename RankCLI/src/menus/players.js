import * as p from "@clack/prompts";
import * as s from "../services/api.js";
import Table from "cli-table3";

function _showTable(players) {
  if (!players || players.length === 0) {
    p.log.warn("Nenhum jogador encontrado.");
    return;
  }
  const table = new Table({
    head: ["ID", "Name", "Category", "Score"],
    style: { head: ["cyan"] },
  });
  players.forEach((j) => {
    table.push([j.id, j.name, j.category, j.score]);
  });
  console.log(table.toString());
}

function _showStats(stats) {
  const table = new Table({ style: { head: ["cyan"] } });
  table.push(
    { "Highest Score": stats.highestScore },
    { "Lowest Score": stats.lowestScore },
    { "Average Score": stats.averageScore.toFixed(2) },
    { "Total Players": stats.totalCount },
  );
  console.log(table.toString());
  const catTable = new Table({
    head: ["Category", "Count"],
    style: { head: ["cyan"] },
  });
  Object.entries(stats.countByCategory).forEach(([cat, count]) => {
    catTable.push([cat, count]);
  });
  console.log(catTable.toString());
}

function _showComparison(results) {
  const table = new Table({
    head: ["Strategy", "Time (ms)", "Time (ns)"],
    style: { head: ["cyan"] },
  });
  results.forEach((r) => {
    table.push([
      r.strategy,
      r.elapsedTimeMillis.toFixed(4),
      r.elapsedTimeNanos,
    ]);
  });
  console.log(table.toString());
  p.log.success(`Fastest: ${results[0].strategy}`);
}

export default async function playersMenu() {
  const action = await p.select({
    message: "Players",
    options: [
      { value: "create", label: "1 - Cadastrar" },
      { value: "getAllPlayers", label: "2 - Listar" },
      { value: "getByName", label: "3 - Buscar" },
      { value: "sort", label: "4 - Ordenar" },
      { value: "stats", label: "5 - Mostrar Estatísticas" },
      { value: "compare", label: "Comparar Performance de Ordenação" },
      { value: "generate", label: "Generate Random Player" },
      { value: "generate_many", label: "Generate Multiple Players" },
      { value: "back", label: "6 - Voltar / Sair" },
    ],
  });

  switch (action) {
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

    case "stats": {
      const stats = await s.getStatistics();
      _showStats(stats);
      break;
    }

    case "compare": {
      const results = await s.compareSortStrategies();
      _showComparison(results);
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

    case "back":
    default:
      return;
  }

  await p.text({ message: "Press ENTER to continue..." });
}
