import { Button } from "@/components/ui/button";
import { Tabs } from "@/components/ui/tabs";
import { CardTitle, CardHeader, CardContent, Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Label } from "@/components/ui/label";

export function ReportViewer() {
  const saraTags = ["General", "Trust and Safety", "User Experience"];

  const cards = [
    {
      title: "Browser errors were logged to the console",
      badge: "General",
      description:
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    },
    {
      title: "Missing source maps for large first-party JavaScript",
      badge: "General",
      description:
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    },
    {
      title: "Ensure CSP is effective against XSS attacks",
      badge: "Trust and Safety",
      description:
        "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.",
    },
    {
      title: "Issues were logged in the Issues panel in Chrome Devtools",
      badge: "General",
      description:
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    },
    {
      title: "Detected JavaScript libraries",
      badge: "General",
      description:
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
    },
  ];

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
          {saraTags.map((tag) => (
            <Button key={tag} variant="ghost">
              {tag}
            </Button>
          ))}
        </div>
      </Tabs>
      <div className="space-y-4">
        {cards.map((card, index) => (
          <Card key={index} className="w-full">
            <CardHeader>
              <CardTitle>{card.title}</CardTitle>
            </CardHeader>
            <CardContent>
              <div className="flex">
                <Badge className="mb-2" variant="outline">
                  {card.badge}
                </Badge>
              </div>
              <p className="text-gray-500">{card.description}</p>
              <Button className="mt-4 text-sm" variant="ghost">
                Expand
              </Button>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}
