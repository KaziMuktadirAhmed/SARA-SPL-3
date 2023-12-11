import fs from "fs/promises"; // Import the fs module with promise-based functions

export async function readJSON(filePath) {
  try {
    const data = await fs.readFile(filePath, "utf8");
    const jsonObject = JSON.parse(data);
    return jsonObject;
  } catch (error) {
    console.error("Error reading JSON file:", error);
    throw error; // Propagate the error for handling elsewhere if needed
  }
}
