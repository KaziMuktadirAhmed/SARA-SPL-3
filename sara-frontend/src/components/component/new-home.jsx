import { CardHeader, CardContent, Card } from "@/components/ui/card";
import { Label } from "@/components/ui/label";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

export function NewHome() {
  return (
    <>
      <div className="container flex flex-col items-center px-4 py-8 mx-auto">
        <div className="mb-8 text-center">
          <h1 className="mb-8 font-bold text-gray-800 text-8xl">SARA</h1>
          <p className="text-xl text-gray-600">
            A post processing tool for static analysis tools
          </p>
        </div>
        <Card className="w-full max-w-md overflow-hidden bg-white rounded-lg shadow-md">
          <CardHeader className="px-6 py-4">
            <h2 className="text-xl font-bold text-gray-800">Resource path</h2>
          </CardHeader>
          <CardContent className="p-6">
            <div className="grid gap-4">
              <div className="grid w-full max-w-sm items-center gap-1.5">
                <Label
                  className="mb-2 text-sm font-bold text-gray-700"
                  htmlFor="filePath"
                >
                  Sarif report path
                </Label>
                <Input
                  id="sarifFilePath"
                  placeholder="Enter file path for sarif report"
                  type="text"
                />
              </div>
              <div className="grid w-full max-w-sm items-center gap-1.5">
                <Label
                  className="mb-2 text-sm font-bold text-gray-700"
                  htmlFor="filePath"
                >
                  Project source path
                </Label>
                <Input
                  className="w-full px-3 py-2 leading-tight text-gray-700 border rounded shadow appearance-none focus:outline-none focus:shadow-outline"
                  placeholder="Enter file path for project source code"
                  id="srcFilePath"
                  type="text"
                />
              </div>
            </div>
            <Button
              className="mt-6 hover:bg-gray-100"
              size="lg"
              variant="outline"
            >
              Start
            </Button>
          </CardContent>
        </Card>
      </div>
    </>
  );
}
