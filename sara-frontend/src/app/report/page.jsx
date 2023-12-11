import { ReportViewer } from "@/components/component/report-viewer";

export default async function Report({ searchParams }) {
  const { sarifFilePath, srcFilePath } = searchParams;
  console.log("from server side: ", sarifFilePath, srcFilePath);
  return (
    <div>
      <ReportViewer />
    </div>
  );
}
