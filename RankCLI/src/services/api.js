const BASE_URL = "http://localhost:8080/ranking";

export async function createPlayer(name, category, score) {
  const res = await fetch(`${BASE_URL}/create`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ name, category, score }),
  });
  return res.json();
}

export async function generatePlayer() {
  const res = await fetch(`${BASE_URL}/generate`, {
    method: "POST",
  });
  return res.ok;
}

export async function generateManyPlayers(num) {
  const res = await fetch(`${BASE_URL}/generate/${num}`, {
    method: "POST",
  });
  return res.json();
}

export async function getAllPlayers() {
  const res = await fetch(`${BASE_URL}/`);
  return res.json();
}

export async function getPlayerByName(nome) {
  const res = await fetch(`${BASE_URL}/${nome}`, {
    method: "GET",
  });
  if (!res.ok) {
    return null;
  }
  return res.json();
}

export async function getStatistics() {
  const res = await fetch(`${BASE_URL}/stats`, {
    method: "GET",
  });
  return res.json();
}

export async function sort(type) {
  const res = await fetch(`${BASE_URL}/sort/${type}`, {
    method: "GET",
  });
  return res.json();
}

export async function compareSortStrategies() {
  const res = await fetch(`${BASE_URL}/sort/compare`, {
    method: "GET",
  });
  return res.json();
}