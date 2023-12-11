import { Button } from "@/components/ui/button";
import { Tabs } from "@/components/ui/tabs";
import { CardTitle, CardHeader, CardContent, Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Label } from "@/components/ui/label";

export function ReportViewer() {
  return (
    <div className="container p-6 mx-auto">
      <h1 className="mb-6 text-3xl font-bold">Static analysis report</h1>
      <Tabs className="flex items-center gap-4 mb-6">
        <Label
          className="text-base font-bold text-gray-700"
          htmlFor="reportType"
        >
          SARA tags:
        </Label>
        <div className="flex space-x-1">
          <Button variant="ghost">All</Button>
          <Button variant="ghost">General</Button>
          <Button variant="ghost">Trust and Safety</Button>
          <Button variant="ghost">User Experience</Button>
        </div>
      </Tabs>
      <div className="space-y-4">
        <Card className="w-full">
          <CardHeader>
            <CardTitle>Browser errors were logged to the console</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex">
              {" "}
              {/* Added flex class */}
              <Badge className="mb-2" variant="outline">
                General
              </Badge>
            </div>
            <p className="text-gray-500">
              Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
              officia deserunt mollit anim id est laborum.
            </p>
            <Button className="mt-4 text-sm" variant="ghost">
              Expand
            </Button>
          </CardContent>
        </Card>
        <Card className="w-full">
          <CardHeader>
            <CardTitle>
              Missing source maps for large first-party JavaScript
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex">
              {" "}
              {/* Added flex class */}
              <Badge className="mb-2" variant="outline">
                General
              </Badge>
            </div>
            <p className="text-gray-500">
              Excepteur sint occaecat cupidatat non proident, sunt in culpa qui
              officia deserunt mollit anim id est laborum.
            </p>
            <Button className="mt-4 text-sm" variant="ghost">
              Expand
            </Button>
          </CardContent>
        </Card>
        <Card className="w-full">
          <CardHeader>
            <CardTitle>Ensure CSP is effective against XSS attacks</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex">
              {" "}
              {/* Added flex class */}
              <Badge className="mb-2" variant="outline">
                Trust and Safety
              </Badge>
            </div>
            <p className="text-gray-500">
              Sed ut perspiciatis unde omnis iste natus error sit voluptatem
              accusantium doloremque laudantium.
            </p>
            <Button className="mt-4 text-sm" variant="ghost">
              Expand
            </Button>
          </CardContent>
        </Card>
        <Card className="w-full">
          <CardHeader>
            <CardTitle>
              Issues were logged in the Issues panel in Chrome Devtools
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex">
              {" "}
              {/* Added flex class */}
              <Badge className="mb-2" variant="outline">
                General
              </Badge>
            </div>
            <p className="text-gray-500">
              Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
              nisi ut aliquip ex ea commodo consequat.
            </p>
            <Button className="mt-4 text-sm" variant="ghost">
              Expand
            </Button>
          </CardContent>
        </Card>
        <Card className="w-full">
          <CardHeader>
            <CardTitle>Detected JavaScript libraries</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex">
              {" "}
              {/* Added flex class */}
              <Badge className="mb-2" variant="outline">
                General
              </Badge>
            </div>
            <p className="text-gray-500">
              Duis aute irure dolor in reprehenderit in voluptate velit esse
              cillum dolore eu fugiat nulla pariatur.
            </p>
            <Button className="mt-4 text-sm" variant="ghost">
              Expand
            </Button>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
