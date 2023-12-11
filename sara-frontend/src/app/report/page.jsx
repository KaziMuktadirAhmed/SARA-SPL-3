import { ReportViewer } from "@/components/component/report-viewer";
import { readJSON } from "@/lib/readJson";
import { runCLICommand } from "@/lib/runCli";

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

  return (
    <div>
      <ReportViewer report={report} />
    </div>
  );
}
