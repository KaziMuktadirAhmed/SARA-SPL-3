import fs from "fs/promises";
import path from "path";

/**
 * Reads JSON files from a directory and returns their contents.
 * @param {string} parentDirectory - The absolute path of the parent directory.
 * @returns {Promise<Array<Object>>} - A promise that resolves to an array of JSON file contents.
 * @throws {Error} - If there is an error reading the JSON files.
 */
export async function readJSONFilesFromDirectory(parentDirectory) {
  try {
    const files = await fs.readdir(parentDirectory);

    const jsonFiles = files.filter((file) => {
      return (
        path.extname(file) === ".json" &&
        !isNaN(parseInt(path.basename(file, ".json")))
      );
    });

    const sortedFiles = jsonFiles.sort((a, b) => parseInt(a) - parseInt(b));

    const fileContents = await Promise.all(
      sortedFiles.map(async (file) => {
        const filePath = path.join(parentDirectory, file);
        const content = await fs.readFile(filePath, "utf-8");
        return JSON.parse(content);
      })
    );

    return fileContents;
  } catch (error) {
    console.error("Error reading JSON files:", error);
    throw error;
  }
}

export default readJSONFilesFromDirectory;
