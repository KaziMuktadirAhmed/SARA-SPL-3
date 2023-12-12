"use client";

import { useState } from "react";

import { Button } from "@/components/ui/button";
import { Tabs } from "@/components/ui/tabs";
import { Label } from "@/components/ui/label";
import { formatSarif } from "@/lib/formatSarif";
import IssueCard from "../ui/issue-card";

export function ReportViewer({ report, targetTags }) {
  console.log("targetTags: ", targetTags);
  const [selectedTab, setSelectedTab] = useState("all");

  const saraTags = ["all", "ui", "network", "storage", "memory", "io"];
  let cards = formatSarif(report);
  // cards = cards.map((card, index) => {
  //   card.saraTags = targetTags[index];
  //   return card;
  // });

  const handleTabClick = (tag) => {
    setSelectedTab(tag);
  };

  const filteredCards =
    selectedTab === "all"
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
