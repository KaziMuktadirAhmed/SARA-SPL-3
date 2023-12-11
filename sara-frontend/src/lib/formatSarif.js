export function formatSarif(sarif) {
  const issueCard = [];
  sarif.runs[0].results.forEach((result) => {
    issueCard.push({
      title: result.message.text,
      description: result.message.text,
      severity: {
        ...getRuleContext(result.rule.index, result.ruleId, sarif),
      },
      tags: result.ruleId,
      properties: {
        "File Path": result.locations[0].physicalLocation.artifactLocation.uri,
        "Line Number": result.locations[0].physicalLocation.region.startLine,
        "Column Number":
          result.locations[0].physicalLocation.region.startColumn,
      },
    });
  });
  return issueCard;
}

function getRuleContext(ruleIndex, ruleId, sarif) {
  if (sarif.runs[0].tool.driver.rules.length >= ruleIndex) {
    // find the rule with the same index as the result
    const rule = sarif.runs[0].tool.driver.rules.find((rule) => {
      return rule.id === ruleId;
    });
    // return the rule's severity
    return {
      level: rule.defaultConfiguration.level,
      shortDescription: rule.shortDescription.text,
      fullDescription: rule.fullDescription.text,
    };
  }

  let foundExtension;
  let foundRule;

  for (let i = 0; i < sarif.runs[0].tool.extensions.length; i++) {
    let ext = sarif.runs[0].tool.extensions[i];

    for (let j = 0; j < ext.rules.length; j++) {
      let rule = ext.rules[j];

      if (rule.id === ruleId) {
        foundRule = rule;
        foundExtension = ext;
        break;
      }
    }

    if (foundRule) {
      break;
    }
  }
  return {
    label: foundRule.defaultConfiguration.level,
    shortDescription: foundRule.shortDescription.text,
    fullDescription: foundRule.fullDescription.text,
  };
}
