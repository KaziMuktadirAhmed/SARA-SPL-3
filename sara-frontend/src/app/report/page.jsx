import { ReportViewer } from "@/components/component/report-viewer";
import { readJSON } from "@/lib/readJson";
import { runCLICommand } from "@/lib/runCli";
import { readJSONFilesFromDirectory } from "@/lib/aggregateSaraTags";

export default async function Report({ searchParams }) {
  const { srcFilePath, sarifFilePath } = searchParams;
  console.log(
    "from server side:",
    "\nsarif: ",
    sarifFilePath,
    "\nsrc root:",
    srcFilePath
  );
  await runCLICommand("java --version");
  const report = await readJSON(sarifFilePath);
  const saraTags = await readJSONFilesFromDirectory(
    "H:\\Developement\\Classwork\\SPL-3\\SARA-SPL-3\\SARA-test-runner\\out\\artifacts\\SARA_test_runner_jar\\saraTags"
  );

  // const saraTags = await readJSONFilesFromDirectory(
  //   "../../../../SARA-test-runner/out/artifacts/SARA_test_runner_jar/saraTags"
  // );
  console.log(saraTags);

  return (
    <div>
      <ReportViewer report={report} />
    </div>
  );
}
