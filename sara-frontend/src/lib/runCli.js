import { exec } from "child_process";

export async function runCLICommand(command) {
  return new Promise((resolve, reject) => {
    const childProcess = exec(command);

    let stdout = "";
    let stderr = "";

    childProcess.stdout.on("data", (data) => {
      stdout += data;
      console.log(`stdout: ${data}`);
    });

    childProcess.stderr.on("data", (data) => {
      stderr += data;
      console.error(`stderr: ${data}`);
    });

    childProcess.on("close", (code) => {
      if (code !== 0) {
        console.error(`Command execution error, exit code ${code}`);
        reject(`Command execution error, exit code ${code}`);
      } else {
        console.log("Command execution successful");
        resolve({ stdout, stderr });
      }
    });
  });
}
