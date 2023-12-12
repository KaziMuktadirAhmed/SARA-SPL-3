"use client";

import { useState } from "react";

import { Button } from "@/components/ui/button";
import { Tabs } from "@/components/ui/tabs";
import { Label } from "@/components/ui/label";
import { formatSarif } from "@/lib/formatSarif";
import IssueCard from "../ui/issue-card";

export function ReportViewer({ report }) {
  console.log("from client side:", report, formatSarif(report));
  const [selectedTab, setSelectedTab] = useState("All");

  const saraTags = ["All", "ui", "network", "storage", "memory", "io"];
  let cards = [
    {
      level: "error",
      title: "Browser errors were logged to the console",
      saraTags: ["General"],
      description:
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    },
    {
      level: "warning",
      title: "Missing source maps for large first-party JavaScript",
      saraTags: ["General"],
      description:
        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    },
    {
      level: "error",
      title: "Ensure CSP is effective against XSS attacks",
      saraTags: ["General", "Trust and Safety"],
      description:
        "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.",
    },
    {
      level: "warning",
      title: "Issues were logged in the Issues panel in Chrome Devtools",
      saraTags: ["General"],
      description:
        "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
    },
    {
      level: "warning",
      title: "Detected JavaScript libraries",
      saraTags: ["General", "User Experience"],
      description:
        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
    },
  ];

  cards = formatSarif(report);

  const handleTabClick = (tag) => {
    setSelectedTab(tag);
  };

  const filteredCards =
    selectedTab === "All"
      ? cards
      : cards.filter((card) => card.saraTags.includes(selectedTab));

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
          <IssueCard key={index} card={card} />
        ))}
      </div>
    </div>
  );
}
