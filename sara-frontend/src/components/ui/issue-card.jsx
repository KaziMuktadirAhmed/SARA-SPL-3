import { CardTitle, CardHeader, CardContent, Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { useState } from "react";

export default function IssueCard({ card }) {
  const [expanded, setExpanded] = useState(false);
  const handleExpandCollapse = () => {
    setExpanded(!expanded);
  };

  console.log("card: ", card);

  return (
    <Card className="w-full">
      <CardHeader>
        <Badge
          className={
            card.level === "error"
              ? "mb-2 w-min border-red-500 text-red-500"
              : "mb-2 w-min border-yellow-500 text-yellow-500"
          }
          variant="outline"
        >
          {card.level}
        </Badge>
        <CardTitle>{card.title}</CardTitle>
      </CardHeader>
      <CardContent>
        <div className="flex gap-2">
          {card.saraTags.map((badge, index) => (
            <Badge key={index} className="mb-2" variant="outline">
              {badge}
            </Badge>
          ))}
        </div>
        {expanded && (
          <div className="flex flex-col gap-4">
            <p className="text-gray-600">{card.description}</p>
            <div className="flex flex-col w-auto gap-1 p-4 bg-gray-300">
              <p className="font-bold text-black">Source file: </p>
              <p className="font-mono text-gray-600">
                {card.properties["File Path"]}
              </p>
              <p className="font-mono text-gray-600">
                {" "}
                at line no {card.properties["Line Number"]}
              </p>
            </div>
            <div className="flex flex-col w-auto gap-1 p-4 bg-gray-300">
              <p className="font-bold text-black">Full description: </p>
              <p className="font-mono text-gray-600">
                {card.severity.fullDescription}
              </p>
            </div>
          </div>
        )}
        <Button
          className="mt-4 text-sm border-2 border-gray-300"
          variant="ghost"
          onClick={handleExpandCollapse}
        >
          {expanded ? "Collapse" : "Expand"}
        </Button>
      </CardContent>
    </Card>
  );
}
