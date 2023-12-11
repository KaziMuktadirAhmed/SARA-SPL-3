import { CardTitle, CardHeader, CardContent, Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { useState } from "react";

export default function IssueCard({ card }) {
  const [expanded, setExpanded] = useState(false);
  const handleExpandCollapse = () => {
    setExpanded(!expanded);
  };

  return (
    <Card className="w-full">
      <CardHeader>
        <CardTitle>{card.title}</CardTitle>
      </CardHeader>
      <CardContent>
        <div className="flex gap-2">
          {card.badge.map((badge, index) => (
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
