"use client";

import { useState } from "react";

import { CardTitle, CardHeader, CardContent, Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Tabs } from "@/components/ui/tabs";
import { Badge } from "@/components/ui/badge";
import { Label } from "@/components/ui/label";
import { formatSarif } from "@/lib/formatSarif";

export function ReportViewer({ report }) {
  console.log("from client side:", report, formatSarif(report));
  const [selectedTab, setSelectedTab] = useState("All");

  const saraTags = ["All", "General", "Trust and Safety", "User Experience"];
  const cards = [
    {
      title: "Browser errors were logged to the console",
      badge: ["General"],
      description:
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    },
    {
      title: "Missing source maps for large first-party JavaScript",
      badge: ["General"],
      description:
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    },
    {
      title: "Ensure CSP is effective against XSS attacks",
      badge: ["General", "Trust and Safety"],
      description:
        "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.",
    },
    {
      title: "Issues were logged in the Issues panel in Chrome Devtools",
      badge: ["General"],
      description:
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    },
    {
      title: "Detected JavaScript libraries",
      badge: ["General", "User Experience"],
      description:
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
    },
  ];

  const handleTabClick = (tag) => {
    setSelectedTab(tag);
  };

  const filteredCards =
    selectedTab === "All"
      ? cards
      : cards.filter((card) => card.badge.includes(selectedTab));

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
          {saraTags.map((tag) => (
            <Button
              className={selectedTab === tag ? "border-black border-2" : ""}
              key={tag}
              variant={selectedTab === tag ? "outline" : "ghost"}
              onClick={() => handleTabClick(tag)}
            >
              {tag}
            </Button>
          ))}
        </div>
      </Tabs>
      <div className="space-y-4">
        {filteredCards.map((card, index) => (
          <Card key={index} className="w-full">
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
              <p className="text-gray-500">{card.description}</p>
              <Button
                className="mt-4 text-sm border-2 border-gray-300"
                variant="ghost"
              >
                Expand
              </Button>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}
