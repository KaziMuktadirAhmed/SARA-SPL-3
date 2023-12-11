import { CardTitle, CardHeader, CardContent, Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { useState } from "react";

export default function IssueCard({ card, targetTag }) {
  const [expanded, setExpanded] = useState(false);
  const handleExpandCollapse = () => {
    setExpanded(!expanded);
  };

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
          <span className="mb-4">tag: </span>
          {card.saraTags.map((badge, index) => (
            <Badge key={index} className="mb-2" variant="outline">
              {badge}
            </Badge>
          ))}
        </div>
        {expanded && <p className="text-gray-500">{card.description}</p>}
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
