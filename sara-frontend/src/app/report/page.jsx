import { ReportViewer } from "@/components/component/report-viewer";
import { readJSON } from "@/lib/readJson";
import { runCLICommand } from "@/lib/runCli";
import { readJSONFilesFromDirectory } from "@/lib/aggregateSaraTags";
import path from "path";

export default async function Report({ searchParams }) {
  const { srcFilePath, sarifFilePath } = searchParams;
  const report = await readJSON(sarifFilePath);
  const results = report.runs[0].results;
  let saraTags;

  await results.forEach(async (result, index) => {
    let location = path.join(
      srcFilePath,
      result.locations[0].physicalLocation.artifactLocation.uri
    );
    let targetLine = result.locations[0].physicalLocation.region.startLine;
    const issueIndex = index;
    console.log("logs:");
    await runCLICommand("dir");
    console.log(
      "command to be executed: ",
      `java -jar .\\SARA-test-runner.jar ${location} ${targetLine} ${issueIndex}`
    );
    await runCLICommand(
      `java -jar .\\SARA-test-runner.jar ${location} ${targetLine} ${issueIndex}`
    );
    saraTags = await readJSONFilesFromDirectory(".\\saraTags");
    console.log(saraTags);
  });

  // const saraTags = await readJSONFilesFromDirectory(
  //   "../../../../SARA-test-runner/out/artifacts/SARA_test_runner_jar/saraTags"
  // );

  return (
    <div>
      <ReportViewer report={report} targetTags={saraTags} />
    </div>
  );
}
